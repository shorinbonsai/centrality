package org.cytoscape.centiscape.internal.Stress;

import java.util.Iterator;
import java.util.Vector;
import org.cytoscape.model.CyNode;

public class StressResult {
  private String source;
  
  private String target;
  
  private Vector Stresselements;
  
  private int SPcount;
  
  public StressResult() {}
  
  public StressResult(String source, String target) {
    this.source = source;
    this.target = target;
    this.Stresselements = new Vector();
    this.SPcount = 1;
  }
  
  public boolean exist(String Source, String Target) {
    if (Source.equals(this.source) && Target.equals(this.target))
      return true; 
    return false;
  }
  
  public void incrementSPcount() {
    this.SPcount++;
  }
  
  public String getsource() {
    return this.source;
  }
  
  public String gettarget() {
    return this.target;
  }
  
  public Vector getVector() {
    return this.Stresselements;
  }
  
  public void update(CyNode node) {
    boolean found = false;
    for (Iterator<StressElement> i = this.Stresselements.iterator(); i.hasNext(); ) {
      StressElement currentelement = i.next();
      if (node.getSUID() == currentelement.getNode().getSUID()) {
        currentelement.incrementSPcount();
        found = true;
      } 
    } 
    if (!found) {
      StressElement currentelement = new StressElement(node);
      this.Stresselements.addElement(currentelement);
    } 
  }
  
  public void calculateStresscount() {
    for (int i = 0; i < this.Stresselements.size(); i++) {
      StressElement currentelement = this.Stresselements.elementAt(i);
      currentelement.calculateStresscount(1.0D);
    } 
  }
  
  public String toString() {
    String result = "source = " + this.source + "target = " + this.target + " numero SP = " + this.SPcount;
    for (Iterator<StressElement> i = this.Stresselements.iterator(); i.hasNext(); ) {
      StressElement currentelement = i.next();
      result = result + " " + currentelement.getNode().getSUID() + "spcount = " + currentelement.getSPcount() + "Stresscount" + currentelement.getStresscount();
    } 
    return result;
  }
}
