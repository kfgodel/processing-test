package app.ar.com.dgarcia.processing.sandbox.vortex.impl;

import app.ar.com.dgarcia.processing.sandbox.vortex.VortexNode;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexTunnel;

/**
 * Created by kfgodel on 18/01/15.
 */
public class NodeImpl implements VortexNode {

    public static NodeImpl create(){
        return new NodeImpl();
    }
}
