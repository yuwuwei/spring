/*
 * This file is generated by jOOQ.
 */
package jp.co.sysystem.springWorkout.domain.jooqObject;


import javax.annotation.processing.Generated;

import jp.co.sysystem.springWorkout.domain.jooqObject.tables.User;
import jp.co.sysystem.springWorkout.domain.jooqObject.tables.Userdetail;


/**
 * Convenience access to all tables in workout
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>workout.user</code>.
     */
    public static final User USER = User.USER;

    /**
     * The table <code>workout.userdetail</code>.
     */
    public static final Userdetail USERDETAIL = Userdetail.USERDETAIL;
}
