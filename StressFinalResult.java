package org.cytoscape.centiscape.internal.Stress;

public class StressFinalResult implements Comparable {
  private String nodename;
  
  private double Stress;
  
  public StressFinalResult() {}
  
  public StressFinalResult(String Nodename, double Stress) {
    this.nodename = Nodename;
    this.Stress = Stress;
  }
  
  public void update(double Stressvalue) {
    this.Stress += Stressvalue;
  }
  
  public boolean Nameequals(String name) {
    return this.nodename.equals(name);
  }
  
  public String toString() {
    String result = "node name= " + this.nodename + " Stress =" + this.Stress;
    return result;
  }
  
  public String getName() {
    return this.nodename;
  }
  
  public double getStress() {
    return this.Stress;
  }
  
  public int compareTo(Object c) {
    StressFinalResult c2 = (StressFinalResult)c;
    if (getStress() > c2.getStress())
      return -1; 
    if (getStress() < c2.getStress())
      return 1; 
    return 0;
  }
}
