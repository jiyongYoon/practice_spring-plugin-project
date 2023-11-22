package org.example.app.connector;

import java.sql.SQLException;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.example.connector.ConnectorDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/connector")
public class ConnectorController {

    private final ConnectionService connectionService;

    @PostMapping
    public Properties createConnector(@RequestBody ConnectorDto connectorDto) throws SQLException {
        return connectionService.createConnector(connectorDto);
    }

    @GetMapping
    public ConnectorDto getConnector(String connName) {
        return connectionService.getConnector(connName);
    }
}
