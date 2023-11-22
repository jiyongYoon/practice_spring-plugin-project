package org.example.app.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.connector.ConnectorFactory;
import org.example.connector.ConnectorManager;
import org.example.connector.DBType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements ApplicationRunner {

    private final ApplicationContext applicationContext;
    private final ConnectionService connectionService;

    @Override
    public void run(ApplicationArguments args) {

        log.info("MyRunner start");
        // 플러그인에서 빈으로 주입된 ConnectorManager 타입의 클래스들을 DB 타입별로 connectionMgr.map에 세팅
        List<ConnectorManager> managers =
            new ArrayList<>(applicationContext.getBeansOfType(ConnectorManager.class).values());
        log.info("add ConnectorManager Beans by Plugins, {}", managers);
        Map<DBType, ConnectorManager> managerMap = connectionService.getManagerMap();
        for (ConnectorManager manager : managers) {
            managerMap.put(manager.getDbType(), manager);
        }

        // 플러그인에서 빈으로 주입된 ConnectorFactory 타입의 클래스들을 DB 타입별로 connectionMgr.map에 세팅
        List<ConnectorFactory> factories =
            new ArrayList<>(applicationContext.getBeansOfType(ConnectorFactory.class).values());
        log.info("add ConnectorFactory Beans by Plugins, {}", factories);
        Map<DBType, ConnectorFactory> factoryMap = connectionService.getFactoryMap();
        for (ConnectorFactory factory : factories) {
            factoryMap.put(factory.getMyType(), factory);
        }
    }
}
