package org.example.app.connector;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.connector.Connector;
import org.example.connector.ConnectorFactory;
import org.example.connector.ConnectorManager;
import org.example.connector.ConnectorDto;
import org.example.connector.DBType;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionService {

    @Getter
    private final Map<DBType, ConnectorManager> managerMap = new HashMap<>();

    @Getter
    private final Map<DBType, ConnectorFactory> factoryMap = new HashMap<>();

    public Properties createConnector(ConnectorDto request) throws SQLException {
        log.info("createConnector()");
        Connector con = null;
        if (request.getDbType().equals(DBType.POSTGRES)) {
            log.info("make POSTGRES connection!!, url={}", request.getUrl());

            con = factoryMap.get(DBType.POSTGRES).getConnector(request);

            ConnectorManager postgresManager = managerMap.get(DBType.POSTGRES);
            postgresManager.getConnectorMap().put(con.getConnName(), con);
        }
        return con.getConnection().getClientInfo();
    }

    public ConnectorDto getConnector(String connName) {
        List<ConnectorManager> collect = managerMap.values().stream().collect(Collectors.toList());
        for (ConnectorManager connectorManager : collect) {
            List<Connector> connectorList = connectorManager.getConnectorMap().values().stream().collect(Collectors.toList());
            for (Connector connector : connectorList) {
                if (connName.equals(connector.getConnName())) {
                    return ConnectorDto.toDto(connector);
                }
            }
        }

        log.info("not found connector");
        throw new RuntimeException("not found connector");
    }

}
