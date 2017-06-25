/***************************************************************************\
 *               *                                                         *
 *    #####      *  (!) 2014 by Giovanni Squillero                         *
 *   ######      *  Politecnico di Torino - Dip. Automatica e Informatica  *
 *   ###   \     *  Cso Duca degli Abruzzi 24 / I-10129 TORINO / ITALY     *
 *    ##G  c\    *                                                         *
 *    #     _\   *  tel : +39-011-564.7092  /  Fax: +39-011-564.7099       *
 *    |   _/     *  mail: giovanni.squillero@polito.it                     *
 *    |  _/      *  www : http://www.cad.polito.it/staff/squillero/        *
 *               *                                                         *
\***************************************************************************/

package it.polito.tdp.bike.db;

import it.polito.tdp.bike.bean.Station;
import it.polito.tdp.bike.bean.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Classe DAO per l'accesso al database {@code babs} (Bay Area Bike Sharing)
 * 
 * @author Fulvio
 * 
 */
public class BikeDAO {

	/**
	 * Interroga il database e restituisce tutti i dati nella tabella
	 * {@code station} sotto forma di un {@link ArrayList} di
	 * {@link Station}.
	 * 
	 * @return la {@link ArrayList} di {@link Station}
	 */
	public List<Station> getAllStations() {

		final String sql = "SELECT * FROM station";

		List<Station> stations = new ArrayList<Station>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Station s = new Station(
						rs.getInt("station_id"),
						rs.getString("name"),
						rs.getDouble("lat"),
						rs.getDouble("long"),
						rs.getInt("dockcount"),
						rs.getString("landmark"),
						rs.getDate("installation")
						);
				stations.add(s);
			}
			return stations;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Interroga il database e restituisce tutti i dati nella tabella
	 * {@code station} sotto forma di un {@link ArrayList} di
	 * {@link Station}.
	 * 
	 * @return la {@link ArrayList} di {@link Station}
	 */
	public List<Trip> getAllTrips(LocalDate d) {

		final String sql = "SELECT * FROM trip WHERE StartDate>= ? AND  EndDate < ?";

		List<Trip> trips = new ArrayList<Trip>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			LocalDateTime d1 = d.atStartOfDay();
			LocalDateTime d2 = d.plusDays(1).atStartOfDay();
			st.setString(1, d1.toString());
			st.setString(2,d2.toString());
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Trip t = new Trip(
						rs.getInt("TripID"),
						rs.getInt("Duration"),
						rs.getTimestamp("StartDate").toLocalDateTime(),
						rs.getString("StartStation"),
						rs.getInt("StartTerminal"),
						rs.getTimestamp("EndDate").toLocalDateTime(),
						rs.getString("EndStation"),
						rs.getInt("EndTerminal"),
						rs.getInt("BikeNum"),
						rs.getString("SubscriptionType"),
						rs.getInt("Zip Code")
						);
				trips.add(t);
			}
			return trips;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public List<String> getAllLandmarks(){
		final String sql = "select distinct(landmark) from station";

		List<String> landmarks = new ArrayList<String>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				landmarks.add(rs.getString("landmark"));
			}
			conn.close();
			return landmarks;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}


	public List<Station> getStationOfCity(String landmark, Map<Integer, Station> stationMap){
		final String sql = "SELECT StartTerminal, SUM(Duration) as somma FROM trip WHERE StartTerminal IN (\r\n" + 
				"SELECT station_id FROM station WHERE landmark= ? ) GROUP BY StartStation";

		List<Station> stations = new ArrayList<>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, landmark);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				stationMap.get(rs.getInt("StartTerminal")).setTotDurata(rs.getInt("somma"));
				stations.add(stationMap.get(rs.getInt("StartTerminal")));
			}
			conn.close();
			return stations;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * Test method for class {@link BikeDAO}
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		
		BikeDAO dao = new BikeDAO() ;
		
		List<Station> list = dao.getAllStations() ;
		
		for( Station s : list ) {
			System.out.format("%-4d %10s %f-%f %d %s\n", 
					s.getStationId(), s.getName(), s.getLat(), s.getLng(), s.getDockCount(), s.getLandmark()) ;
		}
		
		System.out.println(LocalDate.of(2013, 8, 29));
		
		List<Trip> list2 = dao.getAllTrips(LocalDate.of(2013, 8, 29)) ;
		
		for( Trip t : list2 ) {
			System.out.format("%tc %s - %tc %s -> %d\n", 
					t.getStartDate(), t.getStartStation(), t.getEndDate(), t.getEndStation(), t.getDuration()) ;
		}


	}

}
