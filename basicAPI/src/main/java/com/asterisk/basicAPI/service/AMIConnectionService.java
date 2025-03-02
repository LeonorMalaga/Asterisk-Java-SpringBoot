package com.asterisk.basicAPI.service;

import models.AMIConnection;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.telnet.TelnetClient;
/**
 * The {@code AMIConnectionService} class is used to interact with the Asterisk Manager Interface (AMI).
 * It provides functionality to connect to an Asterisk server, retrieve information, and send actions
 * to control or monitor the PBX system.
 **/
@Service
public class AMIConnectionService {

    /**
     * Retrieves the state list of extensions (Base method with all parameters)
     *
     * @param ami               The AMIConnection object.
     * @param maxIterationCount The maximum number of iterations (default: 20000).
     * @param timeout The maximun
     * @return The result as a String.
     */
    public List<Map<String, String>> ExtensionsStateList(AMIConnection ami, int maxIterationCount, int timeout) {

        TelnetClient telnet = new TelnetClient();
        String line = "";
        String result = "";
        List<Map<String, String>> jsonResult = new ArrayList<>();
        int iterationCount = 0;
        try {
            telnet.setDefaultTimeout(5000);
            telnet.connect(ami.getIp(), ami.getPort());

            InputStream input = telnet.getInputStream();
            OutputStream output = telnet.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);
            // 1. Send Login Action
            writer.println("Action: Login");
            writer.println("UserName:" + ami.getUser());
            writer.println("Secret: " + ami.getPass());
            writer.println(); // Mandatory empty line for AMI protocol
            writer.flush();
            System.out.println("Login action sent ");
            // line is for  iterrate along the buffer

            // Read Login Response
            iterationCount = 0;
            while ((line = reader.readLine()) != null && iterationCount < 20) {
                System.out.println(line);
                if (line.contains("Message: Authentication accepted")) {
                    break;
                }
                iterationCount++;
            }

            // 2. Send ExtensionStateList Action
            writer.println("Action: ExtensionStateList");
            writer.println(); // Empty line
            writer.flush();
            // Result is for save the information
            iterationCount = 0;
            while ((line = reader.readLine()) != null && iterationCount < maxIterationCount) {
                System.out.println(line);
                result = result.concat(line).concat(System.lineSeparator());
                if (line.contains("Event: ExtensionStateListComplete")) {
                    break;
                }
                iterationCount++;
            }
            // 3. Send Logoff Action
            writer.println("Action: Logoff");
            writer.println(); // Empty line
            writer.flush();
            System.out.println("Logoff");

            // Read Logoff Response
            iterationCount = 0;
            while ((line = reader.readLine()) != null && iterationCount < 10) {
                System.out.println(line);
                if (line.contains("Response: Success")) {
                    System.out.println("Logoff Successful");
                    break;
                }
                iterationCount++;
            }
            telnet.disconnect();
            System.out.println("Disconnected");
            return this.parseResponseToJson(result);

        } catch (IOException e) {
            System.out.println("Failed to connect");
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", "Failed to connect");
            jsonResult.add(errorMap);
            return jsonResult;
        }finally {
            if (telnet.isConnected()) {
                try {
                    telnet.disconnect();
                    System.out.println("disconnected");
                } catch (IOException e) {
                    System.out.println("Action ExtensionStateListComplete can't AMI disconnected in "+ ami.getIp()+":"+ami.getPort());
                }
            }
        }
    }
    public List<Map<String, String>> ExtensionsStateList(AMIConnection ami, int maxIterationCount) {
        return this.ExtensionsStateList(ami,maxIterationCount,5000);
    }
    public List<Map<String, String>> ExtensionsStateList(AMIConnection ami) {
        return this.ExtensionsStateList(ami,20000,5000);
    }
    //-------------------------- Auxiliary methods------------------------------//
    /**
     * Parses the output of "Action: ExtensionStateList" and converts it into a JSON representation.
     * <p>
     * extracting relevant details :extension number, status, and context.
     * </p>
     *
     * @param rawResponse The raw response string containing extension status information.
     *                    Each line represents a key-value pair or a new event.
     * @return A formatted JSON string representing a list of extensions with their status,
     *         or an error message if JSON conversion fails.
     */
    private List<Map<String, String>> parseResponseToJson(String rawResponse) {
        List<Map<String, String>> extensions = new ArrayList<>();
        String[] lines = rawResponse.split("\n");

        Map<String, String> currentExtension = null;

        for (String line : lines) {
            if (line.startsWith("Event: ExtensionStatus")) {
                if (currentExtension != null) {
                    extensions.add(currentExtension);
                }
                currentExtension = new HashMap<>();
            } else if (line.startsWith("Exten:")) {
                currentExtension.put("exten", line.substring("Exten:".length()).trim());
            } else if (line.startsWith("StatusText:")) {
                currentExtension.put("status", line.substring("StatusText:".length()).trim());
            } else if (line.startsWith("Context:")) {
                currentExtension.put("context", line.substring("Context:".length()).trim());
            }
        }
        // Add the last extension if it exists
        if (currentExtension != null) {
            extensions.add(currentExtension);
        }
        // Convert the list to JSON
        return extensions;
    }
}