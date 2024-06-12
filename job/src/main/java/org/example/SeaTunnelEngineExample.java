package org.example;

import org.apache.seatunnel.core.starter.SeaTunnel;
import org.apache.seatunnel.core.starter.enums.MasterType;
import org.apache.seatunnel.core.starter.exception.CommandException;
import org.apache.seatunnel.core.starter.seatunnel.args.ClientCommandArgs;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @author asura7969
 * @create 2024-03-26-22:08
 */
public class SeaTunnelEngineExample {

    public static void main(String[] args)
            throws FileNotFoundException, URISyntaxException, CommandException {
        String configurePath = args.length > 0 ? args[0] : "/examples/fake_to_console.conf";
        String configFile = getTestConfigFile(configurePath);
        ClientCommandArgs clientCommandArgs = new ClientCommandArgs();
        clientCommandArgs.setConfigFile(configFile);
        clientCommandArgs.setCheckConfig(false);
        clientCommandArgs.setJobName(Paths.get(configFile).getFileName().toString());
        // Change Execution Mode to CLUSTER to use client mode, before do this, you should start
        // SeaTunnelEngineServerExample
        clientCommandArgs.setMasterType(MasterType.LOCAL);
        SeaTunnel.run(clientCommandArgs.buildCommand());
    }

    public static String getTestConfigFile(String configFile)
            throws FileNotFoundException, URISyntaxException {
        URL resource = SeaTunnelEngineExample.class.getResource(configFile);
        if (resource == null) {
            throw new FileNotFoundException("Can't find config file: " + configFile);
        }
        return Paths.get(resource.toURI()).toString();
    }
}
