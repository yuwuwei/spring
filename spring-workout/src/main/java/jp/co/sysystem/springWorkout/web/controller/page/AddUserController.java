
package jp.co.sysystem.springWorkout.web.controller.page;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sysystem.springWorkout.domain.table.User;
import jp.co.sysystem.springWorkout.service.AddUserService;
import jp.co.sysystem.springWorkout.util.MessageUtil;
import jp.co.sysystem.springWorkout.web.form.AddUserForm;
import jp.co.sysystem.springWorkout.web.form.group.AddUserIdGroup;
import jp.co.sysystem.springWorkout.web.form.group.AddUserOtherGroup;
import lombok.extern.slf4j.Slf4j;
/**
 * 新規登録画面コントローラー
 * @version 1.0.0 2020/06/10 新規作成
 */

@Controller
@EnableAutoConfiguration
@Slf4j
public class AddUserController {

  @Autowired
  HttpSession session;

  @Autowired
  public MessageUtil msgutil;

  @Autowired
  public AddUserService adduser;

  /// URL定義
  public static final String ADD_USER_URL = "/addUserPage";
  public static final String ADD_USER_PROCESS_URL = "/addUser";
  public static final String SEARCH_URL = "/search";

  ///ページ定義
  public static final String SEARCH_PAGE = "page/search";
  public static final String ADD_USER_PAGE = "page/add_user";
  public static final String ADD_USER_CONFIRM_PAGE = "page/add_user_confirm";


  /**
   * 新規登録画面遷移処理<br>
   * <pre>
   * 検索成功である場合、検索画面へ遷移する。
   * </pre>
   * @param model
   * @return
   */
  @RequestMapping(value = ADD_USER_URL, method = RequestMethod.GET)
  public String addUser(Model model) {

    // フォームを送信する。
    model.addAttribute("addUserForm",new AddUserForm());

    return ADD_USER_PAGE;
  }

  /**
   * 入力処理<br>
   * <pre>
   * 入力に不備がない場合、新規登録確認画面へ遷移する。
   * </pre>
   * @param form
   * @param bindingResult
   * @param model
   * @return
   * @throws ParseException
   */
  @RequestMapping(value = ADD_USER_PROCESS_URL, method = RequestMethod.POST)
  public String processAdd(
      @Validated({AddUserIdGroup.class, AddUserOtherGroup.class})
      @ModelAttribute AddUserForm form,
      BindingResult bindingResult,
      Model model) throws ParseException {

    // BeanValidationの結果確認
    if (bindingResult.hasErrors()) {
      // 新規登録フォームからの入力値確認結果にエラーがある場合の処理

      model.addAttribute("addUserForm", form);
      return ADD_USER_PAGE;
    }

    // パスワードが一致しなかった場合
    if (!form.getPassword().equals(form.getRePassword())) {

      // エラーメッセージをリソースファイルから取得
      String msg = msgutil.getMessage("dbacces.notSamePass");
      log.debug(msg);
      // エラーメッセージを画面に表示する
      model.addAttribute("msg", msg);

      model.addAttribute("addUserForm", form);

      return ADD_USER_PAGE;
    }


    model.addAttribute("addUserForm", form);

    return ADD_USER_CONFIRM_PAGE;
  }




  /**
   * ユーザーID確認<br>
   * <pre>
   * ID入力に不備がない場合、同一IDがないかをチェックする。
   * </pre>
   * @param form
   * @param bindingResult
   * @param model
   * @return
   */
  @RequestMapping(value = ADD_USER_PROCESS_URL,params = "check" ,method = RequestMethod.POST)
  public String checkIdAddUser(
      @Validated(AddUserIdGroup.class)
      @ModelAttribute AddUserForm form,
      BindingResult bindingResult,
      Model model) {


    // BeanValidationの結果確認
    if (bindingResult.hasErrors()) {
      // 遷移先の新規登録画面で使用するFormを送信
      model.addAttribute("addUserForm", form);
      return ADD_USER_PAGE;
    }

    // ID入力欄が空の場合
    if(form.getId().isEmpty())
    {
      // 遷移先の新規登録画面で使用するFormを送信
      model.addAttribute("addUserForm", form);
      return ADD_USER_PAGE;
    }

    // 同一ID確認処理
    User u =  adduser.checkId(form.getId());
    if (u != null) {
      // ユーザーデータが取得できた場合
      if(u.getId().equals(form.getId())) {
        // 同一IDがあった場合
        String msg = msgutil.getMessage("data.notUse");
        log.debug(msg);
        // 失敗メッセージを画面に表示する
        model.addAttribute("msg", msg);

        // 新規登録画面で使用するFormを送信
        model.addAttribute("addUserForm", form);
        return ADD_USER_PAGE;
      }
    } else {
      // メッセージをリソースファイルから取得
      String msg = msgutil.getMessage("data.canUse");

      // メッセージを画面に表示する
      model.addAttribute("smsg", msg);
    }

    // 新規登録画面で使用するFormを送信
    model.addAttribute("addUserForm", form);

    return ADD_USER_PAGE;

  }
}