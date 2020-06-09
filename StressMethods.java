ackage org.cytoscape.centiscape.internal.Stress;

import java.util.Iterator;
import java.util.Vector;
import org.cytoscape.centiscape.internal.CentiScaPeMultiSPath;
import org.cytoscape.centiscape.internal.CentiScaPeShortestPathList;
import org.cytoscape.model.CyNode;

public class StressMethods {
  public static void updateStress(Vector<CentiScaPeShortestPathList> sptree, Vector StressVectorResults) {
    Vector<StressResult> StressVector = new Vector();
    Vector newresults = new Vector();
    int i;
    for (i = 0; i < sptree.size(); i++) {
      StressResult currentStressresult;
      CentiScaPeShortestPathList currentlist = sptree.get(i);
      String currentsource = ((CentiScaPeMultiSPath)currentlist.getFirst()).getName();
      String currenttarget = ((CentiScaPeMultiSPath)currentlist.getLast()).getName();
      int currentindex = Stressindexof(currentsource, currenttarget, StressVector);
      if (currentindex == -1) {
        currentStressresult = new StressResult(currentsource, currenttarget);
        StressVector.addElement(currentStressresult);
        currentindex = StressVector.size() - 1;
      } else {
        currentStressresult = StressVector.elementAt(currentindex);
        currentStressresult.incrementSPcount();
      } 
      for (int h = 1; h < currentlist.size() - 1; h++) {
        CentiScaPeMultiSPath currentmultispath = (CentiScaPeMultiSPath)currentlist.get(h);
        CyNode a = currentmultispath.getNode();
        currentStressresult.update(a);
      } 
    } 
    for (i = 0; i < StressVector.size(); i++) {
      StressResult currentresult = StressVector.elementAt(i);
      currentresult.calculateStresscount();
      newresults.addAll(currentresult.getVector());
    } 
    StressupdateVectorResults(newresults, StressVectorResults);
  }
  
  public static int Stressindexof(String source, String target, Vector<StressResult> Stressvector) {
    int result = -1;
    for (int i = 0; i < Stressvector.size(); i++) {
      StressResult currentStressresult = Stressvector.elementAt(i);
      if (currentStressresult.exist(source, target)) {
        result = i;
        break;
      } 
    } 
    return result;
  }
  
  public static void Stressupdatevector(Vector<FinalResultStress> finalvector, StressElement element) {
    int position = Stressindexof(element.getNode().getSUID().longValue(), finalvector);
    if (position == -1) {
      FinalResultStress newfinalresult = new FinalResultStress(element.getNode(), element.getStresscount());
      finalvector.addElement(newfinalresult);
    } else {
      FinalResultStress currentfinalresult = finalvector.elementAt(position);
      currentfinalresult.update(element.getStresscount());
    } 
  }
  
  public static int Stressindexof(long nodeSUID, Vector<FinalResultStress> finalvector) {
    int result = -1;
    for (int i = 0; i < finalvector.size(); i++) {
      FinalResultStress currentfinalresult = finalvector.elementAt(i);
      if (currentfinalresult.suidequals(nodeSUID)) {
        result = i;
        break;
      } 
    } 
    return result;
  }
  
  public static void StressupdateVectorResults(Vector<StressElement> newresult, Vector StressVectorResults) {
    for (int i = 0; i < newresult.size(); i++) {
      StressElement currentelement = newresult.elementAt(i);
      Stressinsertnewvalue(currentelement, StressVectorResults);
    } 
  }
  
  public static void Stressinsertnewvalue(StressElement newelement, Vector StressVectorResults) {
    for (Iterator<FinalResultStress> i = StressVectorResults.iterator(); i.hasNext(); ) {
      FinalResultStress current = i.next();
      if (newelement.getNode().equals(current.getNode()))
        current.update(newelement.getStresscount()); 
    } 
  }
}
