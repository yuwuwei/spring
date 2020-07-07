package jp.co.sysystem.springWorkout.domain.table;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTable {

  private String id;

  private String name;

  private String kana;

  private String birth;

  private String club;
}
