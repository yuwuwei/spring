package jp.co.sysystem.springWorkout.domain.jooqRepository;

import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.User.*;
import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.Userdetail.*;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sysystem.springWorkout.domain.table.ResultTable;
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
   * ユーザーIDから検索
   * @param id
   * @return
   */

  public ResultTable findById(@NonNull String id) {
    ResultTable result = dsl.select()
        .from(USER)
        .leftOuterJoin(USERDETAIL).on(USER.ID.eq(USERDETAIL.ID))
        .where(
            USER.ID.eq(id))
        .fetchOneInto(ResultTable.class);
   return result;
  }

  /**
   *
   * ユーザーIDを削除
   * 2つのテーブルのデータをそれぞれ削除
   * @param id
   * @return
   */

  public int findById2(@NonNull String id){

    int result = dsl.delete(USER)
      .where(
          USER.ID.eq(id))
      .execute();
  return result;

  }
  public int findById3(@NonNull String id) {

    int result = dsl.delete(USERDETAIL)
        .where(
            USERDETAIL.ID.eq(id))
        .execute();
    return result;
    }


}
