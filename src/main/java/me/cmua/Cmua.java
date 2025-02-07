/*
					   _ooOoo_
					  o8888888o
					  88" . "88
					  (| -_- |)
					  O\  =  /O
				   ____/`---'\____
				 .'  \\|     |//  `.
				/  \\|||  :  |||//  \
			   /  _||||| -:- |||||-  \
			   |   | \\\  -  /// |   |
			   | \_|  ''\---/''  |   |
			   \  .-\__  `-`  ___/-. /
			 ___`. .'  /--.--\  `. . __
		  ."" '<  `.___\_<|>_/___.'  >'"".
		 | | :  `- \`.;`\ _ /`;.`/ - ` : | |
		 \  \ `-.   \_ __\ /__ _/   .-` /  /
	======`-.____`-.___\_____/___.-`____.-'======
					   `=---='
	^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
				佛祖保佑       永无BUG
*/

package me.cmua;

import me.cmua.api.event.eventbus.EventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;

import static me.cmua.api.concurrent.ConcurrentManager.runBlocking;

public class Cmua implements ClientModInitializer {
    public static final String MOD_ID = "cmua";
    private static final ModMetadata MOD_METADATA;
    public static String MOD_NAME;
    public static String MOD_VERSION;
    public static EventBus EVENT_BUS;

    static {
        // 初始化Mod信息
        MOD_METADATA = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();
        MOD_NAME = MOD_METADATA.getName();
        MOD_VERSION = MOD_METADATA.getVersion().getFriendlyString();

        EVENT_BUS = new EventBus();
    }

    @Override
    public void onInitializeClient() {
        var usedTime = runBlocking(ManagerLoader::load);
        ManagerLoader.logManager.info("Cmua client init use: " + usedTime + "ms");
    }
}
