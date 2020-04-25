package genericCheckpointing.util;

// import genericCheckpointing.server.StoreRestoreI;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyCreator {

    public Object createProxy(Class[] interfaceArray, InvocationHandler handler) {
        return Proxy.newProxyInstance(getClass().getClassLoader(), interfaceArray, handler);
    }
}
