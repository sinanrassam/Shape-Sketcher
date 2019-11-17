/*
 * An interface for representing the property possessed by those shapes which
 * enclose a region (and so have an interior)
 */
package Shapes;

/**
 *
 * @author sinan
 */
public interface EnclosesRegion {
    
    /**
     *
     * @param filled
     */
    public void setFilled(boolean filled);
    
}
