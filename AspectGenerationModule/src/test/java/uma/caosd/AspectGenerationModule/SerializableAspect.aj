package uma.caosd.AspectGenerationModule;

import java.io.Serializable;

public aspect SerializableAspect {
	declare parents : Wrapper implements Serializable;
}
