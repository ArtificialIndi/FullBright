package arti.fullBright;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Line {
	
	private Entity target;
	private float velocity;
	
	void Start() {
		//target = EntityUtils.getClosestEntity(true);
		aimAtTarget();
	}
	
	public void aimAtTarget()
	{
		if(target == null)
			return;
		if(target instanceof EntityLivingBase)
			EntityUtils.faceEntityClient((EntityLivingBase) target);
		return;
	}	
}
