package raf.graffito.dsw.core;

public abstract class GraffLeaf extends GraffNode {

    public GraffLeaf(String name) {
        super(name);
    }

    @Override
    public void ispisi(String naziv) {
        super.ispisi(naziv);
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
