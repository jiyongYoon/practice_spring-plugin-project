package org.example.connector;

/**
 * 플러그인에서 Connector를 생성해서 리턴하는 역할을 하는 클래스. <br>
 * app에서 DB 타입별 Factory.getConnector()를 하면 Connector를 얻을 수 있음.
 */
public interface ConnectorFactory {
    Connector getConnector(ConnectorDto connectorDto);
    DBType getMyType();
}
