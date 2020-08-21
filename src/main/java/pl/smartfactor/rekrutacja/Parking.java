package pl.smartfactor.rekrutacja;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Parking {
  private String name;
  private Map<Integer, Boolean> places;

  @Override
  public String toString() {
    return "Parking o nazwie " + name + " z miejscami " + places;
  }
}
