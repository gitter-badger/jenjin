package com.binarymonks.jj.specs;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.binarymonks.jj.components.Component;
import com.binarymonks.jj.specs.physics.PhysicsNodeSpec;
import com.binarymonks.jj.specs.render.RenderSpec;
import com.binarymonks.jj.things.ThingNode;


public class ThingNodeSpec {

    public RenderSpec renderSpec = new RenderSpec.Null();
    public PhysicsNodeSpec physicsNodeSpec = new PhysicsNodeSpec.Null();
    public ObjectMap<String, Object> properties = new ObjectMap<>();
    public Array<Component> components = new Array<>();
    public String name;

    public ThingNodeSpec setName(String name) {
        this.name = name;
        return this;
    }

    public ThingNodeSpec setRender(RenderSpec renderSpec) {
        this.renderSpec = renderSpec;
        return this;
    }

    public ThingNodeSpec setPhysics(PhysicsNodeSpec physicsNodeSpec) {
        this.physicsNodeSpec = physicsNodeSpec;
        return this;
    }

    public ThingNodeSpec setProperty(String key, Object value){
        properties.put(key,value);
        return this;
    }

    public ThingNodeSpec setProperty(String key){
        properties.put(key, null);
        return this;
    }

    public ThingNodeSpec addComponent(Component component) {
        components.add(component);
        return this;
    }

}
