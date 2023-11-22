package org.example.connector;

import java.sql.Connection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * datasource 정보를 가지고 connection을 가진 클래스. 직접 빈으로 등록되지는 않는다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Connector {
    DBType dbType;
    String url;
    String userName;
    String pw;
    String connName;
    Connection connection = null;

    protected abstract Connector init_connector(ConnectorDto connectorDto);
}
