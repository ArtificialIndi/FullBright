package arti.fullBright;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "bri", name = "fullBright", version = "1.0")

public class Inits {
	
	public static KeyBinding hotkey;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Item/Block init and registering
		//Config handling	
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//Proxy, TileEntity, entity, GUI and Packet registering
		
		hotkey = new KeyBinding("Toggle Fullbright", 29, "key.categories.misc");
		ClientRegistry.registerKeyBinding(hotkey);
		FMLCommonHandler.instance().bus().register(new Keybinds());
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		//
		//System.out.println("Aimbot mod loaded, credit to arti :D");
	}
}
