package Problems.concurrencydesign.TicketBooking;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.time.Instant;
public class A {
    public static void main(String[] args) {

    }
}
class TB {
    private final ConcurrentHashMap<String, Seat> map;
    private final long HOLD_DURATION_MS = 2L * 60 * 1000_000_000; // 2 minutes
    TB(List<String> list) {
        map=new ConcurrentHashMap<>();
        for(String ids:list) {
            map.put(ids,new Seat(ids));
        }
    }
    String holdSeats(List<String> seatIDs) {
        //now first of all just sort the list to avoid deadlock
        List<Seat> seats=new ArrayList<>();
        for(String id:seatIDs) {
            seats.add(map.get(id));
        }
        Collections.sort(seats,(Seat a,Seat b)->{
            return a.seatID.compareTo(b.seatID);
        });
        //lock the seats
        for(Seat seat:seats) {
            seat.getLock().lock();
        }
        //check for availability
        try {
            for(Seat seat:seats) {
                if(seat.status!=SeatStatus.AVAILABLE) {
                    return null;
                }
            }
            String holdID=UUID.randomUUID().toString();
            long expiryTime=Instant.now().getNano()+HOLD_DURATION_MS;
            for(Seat seat:seats) {
                seat.hold(holdID,expiryTime);
            }
            return holdID;
        } catch(Exception e) {}
        finally {
            for(Seat seat:seats) {
                seat.getLock().unlock();
            }
        }
        return null;
    }
    void releaseHold(String holdId) {

        for (Seat seat : map.values()) {
            seat.getLock().lock();
            try {
                if (seat.getStatus() == SeatStatus.HELD && holdId.equals(seat.getholdID()) && seat.isHoldExpired()) {
                    seat.release();
                }
            } finally {
                seat.getLock().unlock();
            }
        }
    }


}
class Seat {
    String seatID;
    SeatStatus status;
    long holdexpiryTime;
    String holdID;
    ReentrantLock lock=new ReentrantLock();
    Seat(String id) {
        this.seatID=id;
        this.status=SeatStatus.AVAILABLE;
    }
    String getSeatID() {
        return this.seatID;
    }
    ReentrantLock getLock() {
        return this.lock;
    }
    SeatStatus getStatus() {
        return this.status;
    }
    void hold(String holdID,long holdexpiryTime) {
        this.status=SeatStatus.HELD;
        this.holdexpiryTime=holdexpiryTime;
        this.holdID=holdID;
    }
    void book() {
        this.status =SeatStatus.BOOKED;
    }
    void release() {
        this.status=SeatStatus.AVAILABLE;
    }
    boolean isHoldExpired() {
        return status.equals(SeatStatus.HELD) && holdexpiryTime<Instant.now().getNano();
    }
    String getholdID() {
        return holdID;
    }
}
enum SeatStatus {
    AVAILABLE,
    HELD,
    BOOKED
}