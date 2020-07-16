package jp.co.sysystem.springWorkout.domain.table;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 検索結果保持用クラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTable {

  private String id;

  private String name;

  private String kana;

  /**
   * Date型を指定のパターンにフォーマットする
   */
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date birth;

  private String club;

  private int no;
}
