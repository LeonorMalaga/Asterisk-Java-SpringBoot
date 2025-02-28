package com.asterisk.API.models;
import jakarta.persistence.*;
import lombok.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * Entity class representing an AMI (Asterisk Manager Interface) connection.
 * <p>
 * This class defines the connection configuration to interact with the Asterisk system,
 * including the IP address, port, user credentials, task type, and the connection status.
 * The connection defaults to some standard values, like the IP address "127.0.0.1" (localhost)
 * and port 5038, but these can be modified when needed.
 * </p>
 *
 * @author Leonor Martinez
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AMIConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private InetAddress ip= defaultIp();;
    private int port = 5038;
    private String user = "myUser";
    private String password = "mySecret";
    private String AMITask = "extendsionsStateList";
    private String status = "inactive";
    // Default IP address method
    private InetAddress defaultIp() {
        try {
            return InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to set default IP address", e);
        }
    }

}
