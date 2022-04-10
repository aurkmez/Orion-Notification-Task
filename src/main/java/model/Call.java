package model;

import org.springframework.beans.factory.annotation.Autowired;
import service.CallRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * This class has been created to keep Calls between Clients.
 * id is a primary key for the entity but the pair of (callerNum, calledNum) is used as primary key as well.
 * */

@Entity
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int callerNum;
    private int calledNum;
    private Date timestamp;
    private boolean bIsSuccessful; // indicates if Clients contacted or not

    @Autowired
    private CallRepository callRepository;

    public Call(int callerNum, int calledNum, Date timestamp, boolean bIsSuccessful) {
        this.callerNum = callerNum;
        this.calledNum = calledNum;

        this.timestamp = timestamp;
        bIsSuccessful = bIsSuccessful;

        // TODO: should not be here. would be better to have CallService
        callRepository.save(this);
    }

    public int getCallerNum() {
        return callerNum;
    }

    public int getCalledNum() {
        return calledNum;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getId() {
        return id;
    }

    public boolean isbIsSuccessful() {
        return bIsSuccessful;
    }

    public void setCallerNum(int callerNum) {
        this.callerNum = callerNum;
    }

    public void setCalledNum(int calledNum) {
        this.calledNum = calledNum;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setbIsSuccessful(boolean bIsSuccessful) {
        this.bIsSuccessful = bIsSuccessful;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Call)) return false;
        Call call = (Call) o;
        return getId() == call.getId() &&
                getCallerNum() == call.getCallerNum() &&
                getCalledNum() == call.getCalledNum() &&
                isbIsSuccessful() == call.isbIsSuccessful() &&
                Objects.equals(getTimestamp(), call.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCallerNum(), getCalledNum(), getTimestamp(), isbIsSuccessful());
    }
}