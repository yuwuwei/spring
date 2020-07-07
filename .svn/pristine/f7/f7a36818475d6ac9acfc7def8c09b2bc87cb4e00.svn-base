package jp.co.sysystem.springWorkout.service;

import java.util.Date;

import org.jooq.exception.DataAccessException;
import org.jooq.exception.MappingException;
import org.jooq.exception.TooManyRowsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sysystem.springWorkout.domain.jooqRepository.UpdateUserJooqRepository;
import jp.co.sysystem.springWorkout.web.form.UpdateUserForm;
import lombok.NonNull;

/**
 * 更新処理定義クラス
 * @version 1.0.0 2020/06/16 新規作成
 */
@Service
@Transactional(readOnly = true)
public class UpdateService {

  /**
   * JOOQによるデータ取得処理
   */
  @Autowired
  private UpdateUserJooqRepository jrep;

  /**
   * 検索画面で更新対象として選択したレコードのidをもとに、データを取得
   * @param id
   * @return UpdateUserForm
   * @throws DataAccessException
   * @throws MappingException
   * @throws TooManyRowsException
   */
  @Transactional(readOnly = true)
  public UpdateUserForm getUserData(@NonNull String id)
      throws DataAccessException, MappingException, TooManyRowsException {
    UpdateUserForm form =  jrep.selectUserAndUserdetail(id);
    return form;
  }


  /**
   * フォームで入力した情報をもとにデータを更新
   * @param id
   * @param name
   * @param kana
   * @param birth
   * @param club
   * @throws DataAccessException
   */
  @Transactional(readOnly = false)
  public void updateRecords(@NonNull String id, @NonNull String name, @NonNull String kana,
                              @NonNull Date birth, String club) throws DataAccessException {

    // ユーザーマスタを更新
    jrep.updateUser(id, name, kana);

    // ユーザーマスタ詳細を更新
    jrep.updateUserDetail(id, birth, club);
  }
}