package raf.graffito.dsw.core;

public abstract class GraffLeaf extends GraffNode {

    public GraffLeaf(String name, GraffNode parent) {
        super(name,parent);
    }

    @Override
    public GraffNode findByName(String name)
    {
        if (this.name.equals(name))
        {
            return this;
        }
        return null;
    }
}
