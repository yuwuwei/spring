package jp.co.sysystem.springWorkout.domain.jooqRepository;

import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.User.*;
import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.Userdetail.*;

import java.sql.Timestamp;
import java.util.Date;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.MappingException;
import org.jooq.exception.TooManyRowsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sysystem.springWorkout.web.form.UpdateUserForm;
import lombok.NonNull;

/**
 * JOOQを利用したクエリを定義するクラス<br>
 * O/Rマッパーを利用したタイプセーフなクエリ実装を目指す。
 * @see <a href="https://www.jooq.org/">jooq.org</a>
 * @version 1.0.0 2020/06/10 新規作成
 */
@Component
public class UpdateUserJooqRepository {
  @Autowired
  private DSLContext dsl;

  /**
   * idを指定してユーザーマスタとユーザーマスタ詳細のデータを取得
   * ユーザーマスタとユーザーマスタ詳細を外部結合
   * @param id
   * @return ResultTable
   * @throws DataAccessException
   * @throws MappingException
   * @throws TooManyRowsException
   */
  public UpdateUserForm selectUserAndUserdetail(@NonNull String id)
      throws DataAccessException, MappingException, TooManyRowsException {

    UpdateUserForm form = dsl.select()
        .from(USER)
        .leftOuterJoin(USERDETAIL).on(USER.ID.eq(USERDETAIL.ID))
        .where(USER.ID.eq(id))
        .fetchOneInto(UpdateUserForm.class);
   return form;

  }

  /**
   * ユーザーマスタのデータを更新
   * @param id
   * @param name
   * @param kana
   */
  public void updateUser(@NonNull String id, @NonNull String name, @NonNull String kana)
      throws DataAccessException {

    // ユーザーマスタ更新処理
    dsl.update(USER)
        .set(USER.NAME, name)
        .set(USER.KANA, kana)
        .where(USER.ID.eq(id))
        .execute();
  }

  /**
   * ユーザーマスタ詳細のデータを更新
   * @param id
   * @param birth
   * @param club
   */
  public void updateUserDetail(@NonNull String id, @NonNull Date birth, String club ) {
    // ユーザーマスタ詳細更新処理
    dsl.update(USERDETAIL)
        .set(USERDETAIL.BIRTH, new Timestamp(birth.getTime()))
        .set(USERDETAIL.CLUB, club)
        .where(USERDETAIL.ID.eq(id))
        .execute();
  }
}
