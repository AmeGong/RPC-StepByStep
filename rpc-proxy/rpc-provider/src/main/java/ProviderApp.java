import com.ame.HelloService;
import com.ame.server.RpcServer;
import com.ame.server.impl.RpcServerImpl;
import com.ame.service.impl.HelloServiceImpl;

/**
 * FileName: ProviderApp
 * Author:   AmeGong
 * Date:     2020/12/24 15:12
 */
public class ProviderApp {
    public static void main(String[] args) throws Exception {
        RpcServer rpcServer = new RpcServerImpl(8989, 10);
        rpcServer.register(HelloService.class.getName(), HelloServiceImpl.class);
        rpcServer.start();
    }
}
