package com.asterisk.API.dto;

import com.asterisk.API.models.AMIConnection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Data Transfer Object (DTO) for {@link AMIConnection} entity.
 *
 * This DTO is designed to no expose sensitive information:
 * <ul>
 *     <li>IP address (masked to "0.0.0.0")</li>
 *     <li>User credentials (masked as "****")</li>
 *     <li>Password (masked as "****")</li>
 *     <li>Port (masked to a default value of 0)</li>
 * </ul>
 *  * @author Leonor Martinez
 *  * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
public class AMIConnectionDto {
    private Long id;
    private InetAddress ip;
    private int port;
    private String user;
    private String password;
    private String AMITask;
    private String status;
    public AMIConnectionDto(AMIConnection AMIConnection)
    {
        this.id = AMIConnection.getId();
        this.ip = AMIConnection.getIp();
        this.port = AMIConnection.getPort();
        this.user = AMIConnection.getUser();
        this.password = AMIConnection.getPassword();
        this.AMITask = AMIConnection.getAMITask();
        this.status = AMIConnection.getStatus();
    }
    /**
     * Returns the username.
     * The username is masked to prevent exposure of sensitive information.
     *
     * @return the username (masked as "****").
     */
    public String getUser() {
        return "****";
    }
    /**
     * Returns the password.
     * The password is masked to prevent exposure of sensitive credentials.
     *
     * @return the password (masked as "****").
     */
    public String getPassword() {
        return "****";
    }
    /**
     * Returns the port number.
     * The port is masked for security and always returns a default value of 0.
     *
     * @return the port number (masked as 0).
     */
    public int getPort() {
        return 0;
    }
    /**
     * Returns the IP address.
     * The actual IP is masked for security purposes and always returns an InetAddress for "0.0.0.0".
     *
     * @return a masked InetAddress object (representing "0.0.0.0").
     */
    public InetAddress getIp() {
        try {
            return InetAddress.getByName("0.0.0.0");
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to get the masked IP address", e);
        }
    }

}
