package jp.co.sysystem.springWorkout.domain.jooqRepository;

import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.User.*;
import static jp.co.sysystem.springWorkout.domain.jooqObject.tables.Userdetail.*;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sysystem.springWorkout.domain.table.User;
import lombok.NonNull;

/**
 * JOOQを利用したクエリを定義するクラス<br>
 * O/Rマッパーを利用したタイプセーフなクエリ実装を目指す。
 * @see <a href="https://www.jooq.org/">jooq.org</a>
 * @version 1.0.0 2020/06/16 新規作成
 */
@Component
public class SearchUserJooqRepository {
  @Autowired
  private DSLContext dsl;

/**
 * ユーザーマスタとユーザーマスタ詳細を外部結合(本番では内部結合でもよい)
 * ユーザーID,名前、カナで前方一致検索
 * @param id
 * @param name
 * @param kana
 * @return
 */
  public Result<Record> findByIdAndNameAndKana(@NonNull String id, @NonNull String name, @NonNull String kana) {
    Result<Record> result = dsl.select()
        .from(USER)
        .leftOuterJoin(USERDETAIL).on(USER.ID.eq(USERDETAIL.ID))
        .where(USER.ID.like(id + "%"))
        .and(USER.NAME.like(name + "%"))
        .and(USER.KANA.like(kana + "%"))
        .fetch();
   return result;
  }
}
