package org.cytoscape.centiscape.internal.Stress;

import org.cytoscape.model.CyNode;

public class StressElement {
  private long nodeSUID;
  
  private CyNode node;
  
  private int SPcount;
  
  private double Stresscount;
  
  public StressElement() {}
  
  public StressElement(CyNode node) {
    this.node = node;
    this.nodeSUID = node.getSUID().longValue();
    this.SPcount = 1;
    this.Stresscount = 0.0D;
  }
  
  public void incrementSPcount() {
    this.SPcount++;
  }
  
  public CyNode getNode() {
    return this.node;
  }
  
  public int getSPcount() {
    return this.SPcount;
  }
  
  public double getStresscount() {
    return this.Stresscount;
  }
  
  public void calculateStresscount(double totalSP) {
    this.Stresscount = this.SPcount / totalSP;
  }
  
  public String toString() {
    return "nodoname = " + this.nodeSUID + " spcount= " + this.SPcount + " Stresscount= " + this.Stresscount;
  }
}
