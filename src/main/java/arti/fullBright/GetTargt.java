package arti.fullBright;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;

public class GetTargt {
	public static EntityLivingBase getTarget(float par1, double distance)
	{
	    Minecraft mc = Minecraft.getMinecraft();

	    Entity pointedEntity;
	    double d0 = distance;
	    MovingObjectPosition omo = mc.renderViewEntity.rayTrace(d0, par1);
	    double d1 = d0;
	    Vec3 vec3 = mc.renderViewEntity.getPosition(par1);
	    Vec3 vec31 = mc.renderViewEntity.getLook(par1);
	    Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
	    pointedEntity = null;
	    Vec3 vec33 = null;
	    float f1 = 1.0F;
	    @SuppressWarnings("rawtypes")
		List list = mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.renderViewEntity, mc.renderViewEntity.boundingBox.addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand((double)f1, (double)f1, (double)f1));
	    double d2 = d1;

	    for (int i = 0; i < list.size(); ++i)
	    {
	        Entity entity = (Entity)list.get(i);
	        if (entity.canBeCollidedWith())
	        {
	            float f2 = entity.getCollisionBorderSize();
	            AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
	            MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

	            if (axisalignedbb.isVecInside(vec3))
	            {
	                if (0.0D < d2 || d2 == 0.0D)
	                {
	                    pointedEntity = entity;
	                    vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
	                    d2 = 0.0D;
	                }
	            }
	            else if (movingobjectposition != null)
	            {
	                double d3 = vec3.distanceTo(movingobjectposition.hitVec);

	                if (d3 < d2 || d2 == 0.0D)
	                {
	                    if (entity == mc.renderViewEntity.ridingEntity && !entity.canRiderInteract())
	                    {
	                        if (d2 == 0.0D)
	                        {
	                            pointedEntity = entity;
	                            vec33 = movingobjectposition.hitVec;
	                        }
	                    }
	                    else
	                    {
	                        pointedEntity = entity;
	                        vec33 = movingobjectposition.hitVec;
	                        d2 = d3;
	                    }
	                }
	            }
	        }
	    }
	    if (pointedEntity != null && (d2 < d1 || omo == null))
	    {
	        omo = new MovingObjectPosition(pointedEntity, vec33);

	        if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame)
	        {
	            mc.pointedEntity = pointedEntity;
	        }
	    }
	    if (omo != null)
	    {
	        if (omo.typeOfHit == MovingObjectType.ENTITY)
	        {
	            if(omo.entityHit instanceof EntityLivingBase)
	            {
	                return (EntityLivingBase)omo.entityHit;
	            }
	        }
	    }
	    return null;
	}
}
