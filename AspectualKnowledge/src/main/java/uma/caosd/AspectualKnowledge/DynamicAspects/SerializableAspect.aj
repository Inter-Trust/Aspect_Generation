package uma.caosd.AspectualKnowledge.DynamicAspects;

import java.io.Serializable;

public aspect SerializableAspect {
	// Implements serializable
	declare parents : uma.caosd.AspectualKnowledge.* implements Serializable;
	declare parents : uma.caosd.AspectualKnowledge.DynamicAspects.AdvisorsRepository implements Serializable;
	declare parents : uma.caosd.AspectualKnowledge.DynamicAspects.UsersAdvisorsRepository implements Serializable;
	declare parents : uma.caosd.AspectualKnowledge.DynamicAspects.DynamicRepository implements Serializable;
}
