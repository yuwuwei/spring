package jp.co.sysystem.springWorkout.domain.jooqRepository;

import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.User.*;
import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.Userdetail.*;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sysystem.springWorkout.web.form.UpdateUserForm;
import lombok.NonNull;

/**
 * JOOQを利用したクエリを定義するクラス<br>
 * O/Rマッパーを利用したタイプセーフなクエリ実装を目指す。
 * @see <a href="https://www.jooq.org/">jooq.org</a>
 * @version 1.0.0 2020/06/24 新規作成
 */


@Component

public class DeleteUserJooqRepository {
  @Autowired
  private DSLContext dsl;

  /**
   * ユーザーマスタとユーザーマスタ詳細を外部結合(本番では内部結合でもよい)
   * 削除のため
   * ユーザーIDから検索
   * @param id
   * @return
   */

  public UpdateUserForm findById(@NonNull String id) {
    UpdateUserForm form = dsl.select()
        .from(USER)
        .leftOuterJoin(USERDETAIL).on(USER.ID.eq(USERDETAIL.ID))
        .where(
            USER.ID.eq(id))
        .forUpdate()
        .fetchOneInto(UpdateUserForm.class);
   return form;
  }

  /**
   *
   * ユーザーIDを削除
   * 2つのテーブルのデータをそれぞれ削除
   * @param id
   * @return
   */

  public void userDelete(@NonNull String id){

   dsl.delete(USER)
      .where(
          USER.ID.eq(id))
      .execute();

  }
  public void userDetailDelete(@NonNull String id) {

    dsl.delete(USERDETAIL)
        .where(
            USERDETAIL.ID.eq(id))
        .execute();

    }



  }


