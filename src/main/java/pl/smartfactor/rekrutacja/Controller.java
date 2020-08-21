package pl.smartfactor.rekrutacja;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
@Slf4j
public class Controller {

  @GetMapping
  public List<Parking> queryParkings() {
    Map<Integer, Boolean> parkingPlaces = new HashMap<>();
    parkingPlaces.put(1, true);
    parkingPlaces.put(2, true);
    parkingPlaces.put(3, false);
    parkingPlaces.put(4, true);
    parkingPlaces.put(5, false);
    parkingPlaces.put(6, false);
    parkingPlaces.put(7, true);
    parkingPlaces.put(8, false);

    Map<Integer, Boolean> parkingPlaces1 = new HashMap<>();
    parkingPlaces1.put(1, false);
    parkingPlaces1.put(2, true);
    parkingPlaces1.put(3, false);
    parkingPlaces1.put(4, false);
    parkingPlaces1.put(5, false);
    parkingPlaces1.put(6, true);
    parkingPlaces1.put(7, true);
    parkingPlaces1.put(8, false);

    Map<Integer, Boolean> parkingPlaces2 = new HashMap<>();
    parkingPlaces2.put(1, true);
    parkingPlaces2.put(2, false);
    parkingPlaces2.put(3, false);
    parkingPlaces2.put(4, true);
    parkingPlaces2.put(5, true);
    parkingPlaces2.put(6, false);
    parkingPlaces2.put(7, true);
    parkingPlaces2.put(8, true);

    return Arrays.asList(
        new Parking("Parking Główny", parkingPlaces),
        new Parking("Parking Mały", parkingPlaces1),
        new Parking("Parking Duży", parkingPlaces2));
  }

  @PostMapping("/{name}")
  public HttpStatus addParking(
      HttpServletRequest request,
      @PathVariable String name,
      @RequestBody Map<Integer, Boolean> places) {
    final String validToken = "6fee3b56-d8f0-4d68-a4da-3378970237da";

    String login = request.getHeader("X-AUTH-LOGIN");
    String token = request.getHeader("X-AUTH-TOKEN");

    if (login == null || !validToken.equals(token)) {
      return HttpStatus.UNAUTHORIZED;
    }
    Parking parking = new Parking(name, places);
    log.info("Użytkownik " + login + " dodał parking " + parking);
    return HttpStatus.OK;
  }
}
