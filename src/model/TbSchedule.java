package model;

import java.util.Date;

/**
 * 员工实体类
 *
 * @author baito
 */
public class TbSchedule {
    private int id;
    private String person_id;
    private String person_name;
    private Date schedule_start;
    private Date schedule_end;
    private String schedule_place;
    private String schedule_reason;
    private String explains;


    public String getPerson_name() {
        return person_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getSchedule_start() {
        return schedule_start;
    }


    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public void setSchedule_start(Date schedule_start) {
        this.schedule_start = schedule_start;
    }

    public void setSchedule_end(Date schedule_end) {
        this.schedule_end = schedule_end;
    }

    public void setSchedule_place(String schedule_place) {
        this.schedule_place = schedule_place;
    }

    public void setSchedule_reason(String schedule_reason) {
        this.schedule_reason = schedule_reason;
    }


    public void setExplains(String explains) {
        this.explains = explains;
    }

    public Date getSchedule_end() {
        return schedule_end;
    }

    public String getSchedule_place() {
        return schedule_place;
    }

    public String getSchedule_reason() {
        return schedule_reason;
    }

    public String getExplains() {
        return explains;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "TbSchedule{" +
                "id=" + id +
                ", person_id='" + person_id + '\'' +
                ", person_name='" + person_name + '\'' +
                ", schedule_start=" + schedule_start +
                ", schedule_end=" + schedule_end +
                ", schedule_place='" + schedule_place + '\'' +
                ", schedule_reason='" + schedule_reason + '\'' +
                ", explains='" + explains + '\'' +
                '}';
    }
}
