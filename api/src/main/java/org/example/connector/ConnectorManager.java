package org.example.connector;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DB 타입별 Connector를 관리하는 클래스. <br>
 * 빈으로 등록이 되며, DB 타입별 커넥터는 고유 이름으로 식별되어 Map에 저장된다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ConnectorManager {
    Map<String, Connector> connectorMap;
    DBType dbType;

    public abstract void initManager();
}
