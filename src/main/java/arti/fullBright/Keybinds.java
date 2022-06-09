package arti.fullBright;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;


public class Keybinds
{
	private boolean toggled = false;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(InputEvent.KeyInputEvent event) {
		KeyBinding hotkey = Inits.hotkey;
		if (hotkey.isPressed()) {
			//System.out.println("Keybind pressed");
			Start();
		}
	}
	
	
	private Entity target;
	private float velocity;
	
	void Start() {
		target = GetTargt.getTarget(1.0f, 100);
		if(target != null && !toggled) {
			toggled = true;
		}
		else {
			toggled = false;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onTick(TickEvent.RenderTickEvent event) {
		if(toggled) {
			aimAtTarget();
		}
	}
	
	public void aimAtTarget()
	{
		if(target == null) {
			return;
		}
		if(target instanceof EntityLivingBase)
			EntityUtils.faceEntityClient((EntityLivingBase) target);
		return;
	}	
}