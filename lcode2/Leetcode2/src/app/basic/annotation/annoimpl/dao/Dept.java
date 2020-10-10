package app.basic.annotation.annoimpl.dao;

@TableName("dept_table")
public class Dept {
    @ColumnName("dept_name")
    private String deptName;
    @ColumnName("crew_num")
    private Integer crewNum;
    @ColumnName("dept_id")
    private Integer deptId;

    public void setCrewNum(Integer crewNum) {
        this.crewNum = crewNum;
    }

    public Integer getCrewNum() {
        return crewNum;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getDeptId() {
        return deptId;
    }
}
