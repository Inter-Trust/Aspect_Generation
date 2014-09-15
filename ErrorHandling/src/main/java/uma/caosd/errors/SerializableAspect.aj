package uma.caosd.AspectualKnowledge.DynamicAspects;

import java.io.Serializable;

public aspect SerializableAspect {
	// Implements serializable
	declare parents : uma.caosd.errors.* implements Serializable;
	declare parents : uma.caosd.errorHandling.DeploymentStatusSingleton implements Serializable;
}
