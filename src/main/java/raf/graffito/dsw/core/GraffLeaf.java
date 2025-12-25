package raf.graffito.dsw.core;
//Deo Composite sablona, koji ne moze da ima decu
public abstract class GraffLeaf extends GraffNode {

    public GraffLeaf() {
        super();
    }

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
