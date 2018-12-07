package jac444.wk5;

import java.io.Serializable;

/**
 * Student class which implements the Serializable interface and has accessor
 * and mutator methods
 *
 * @author sahil
 */
@SuppressWarnings("serial")
public class Student implements Serializable {
    private String name;
    private String course;
    private Integer grade;
    private Integer id;

    public Student() {
    }
/**
 * Constructor to set the values to the table items
 * @param id
 * @param name of student
 * @param course
 * @param grade
 * @throws Exception if input is not valid
 */
    public Student(Integer id, String name, String course, Integer grade) throws Exception {
    	this.id = id;
    	setCourse(course);
        setGrade(grade);
        setName(name);
    }

    /**
     * Accessor method for variable course
     *
     * @return course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Mutator method for variable course
     *
     * @param course
     * @throws Exception 
     */
    public void setCourse(String course) throws Exception {
    	if (course.trim().equals("")) {
    		throw new Exception("Please enter course.");
    	}
        this.course = course;
    }

    /**
     * Accessor method for variable grade
     *
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Mutator method for variable grade
     *
     * @param grade
     * @throws Exception if grade is lower than 0 or higher than 100
     */
   
	public void setGrade(Integer grade) throws Exception {
        if (grade < 0 || grade > 100)
            throw new Exception("Grade should be in between 0 to 100.");
        this.grade = grade;
    }

    /**
     * Accessor method for variable name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for variable name
     *
     * @param name
     * @throws Exception if the field is empty
     */
    public void setName(String name) throws Exception {
    	if (name.trim().equals("")) {
    		throw new Exception("Please enter name.");
    	}
    	 this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}



