package com.binarymonks.jj.specs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.binarymonks.jj.pools.N;
import com.binarymonks.jj.things.InstanceParams;


public class SceneSpec {

    int pieceIDCounter = 0;

    public ObjectMap<Integer, InstanceSpec> thingPieces = new ObjectMap<>();
    public Array<JointSpec> joints = new Array<>();


    public int addThingSpec(String thingSpecPath, InstanceParams instanceParams) {
        int id = pieceIDCounter++;
        thingPieces.put(id, new InstanceSpec(thingSpecPath, instanceParams));
        return id;
    }

    public void addJoint(int thingAID, int thingBID, JointDef jointDef) {
        joints.add(new JointSpec(thingAID, thingBID, jointDef));
    }

    public static class JointSpec {
        public int thingAID;
        public int thingBID;
        public JointDef jointDef;

        public JointSpec(int thingAID, int thingBID, JointDef jointDef) {
            this.thingAID = thingAID;
            this.thingBID = thingBID;
            this.jointDef = jointDef;

        }
    }
}
