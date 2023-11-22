package org.example.connector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConnectorDto {
    private DBType dbType;
    private String url;
    private String userName;
    private String pw;
    private String connName;

    public static ConnectorDto toDto(Connector connector) {
        return ConnectorDto.builder()
            .dbType(connector.getDbType())
            .url(connector.getUrl())
            .userName(connector.getUserName())
            .connName(connector.getConnName())
            .build();
    }
}
