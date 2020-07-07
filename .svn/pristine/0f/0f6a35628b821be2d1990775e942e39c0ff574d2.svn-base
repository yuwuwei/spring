
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sysystem.springWorkout.service.AddUserConfirmService;
import jp.co.sysystem.springWorkout.util.MessageUtil;
import jp.co.sysystem.springWorkout.web.form.AddUserForm;
import jp.co.sysystem.springWorkout.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
/**
 * 新規登録確認画面コントローラー
 * @version 1.0.0 2020/06/10 新規作成
 */

@Controller
@EnableAutoConfiguration
@Slf4j
public class AddUserConfirmController {

  @Autowired
  HttpSession session;

  @Autowired
  public MessageUtil msgutil;

  @Autowired
  public AddUserConfirmService adduser;

  /// URL定義
  public static final String ADD_USER_CONFIRM_URL = "/addUserConfirmPage";
  public static final String ADD_USER_URL = "/addUserPage";
  public static final String SEARCH_FORM_URL = "/searchPage";

  ///ページ定義
  public static final String SEARCH_PAGE = "page/search";
  public static final String ADD_USER_PAGE = "page/add_user";
  public static final String ADD_USER_CONFIRM_PAGE = "page/add_user_confirm";


  /**
   * 登録処理<br>
   * <pre>
   * 登録成功である場合、検索画面へ遷移する。
   * </pre>
   * @param form
   * @param bindingResult
   * @param model
   * @return
   * @throws ParseException
   */
  @RequestMapping(value = ADD_USER_CONFIRM_URL, method = RequestMethod.POST)
  public String processAddUser(
      @Validated @ModelAttribute AddUserForm form,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes,
      Model model) throws ParseException {


    // BeanValidationの結果確認
    if (bindingResult.hasErrors()) {

      // エラーメッセージをリソースファイルから取得
      String msg = msgutil.getMessage("dbacces.registFailed");
      log.debug(msg);
      // エラーメッセージを画面に表示する
      model.addAttribute("msg", msg);

      model.addAttribute("addUserForm", form);
      return ADD_USER_CONFIRM_PAGE;
    }

    // 入力した値をデータベースに追加する。
    try {
      adduser.InsertUser(form.getId(), form.getPassword(), form.getName(), form.getKana(),
          form.getBirth(),form.getClub());
      //adduser.InsertUserdetail(form.getId(),form.getBirth(),form.getClub());
    }catch(Exception e) {
      // 登録処理に失敗した場合
      String msg = msgutil.getMessage("dbacces.registFailed");
      log.debug(msg);
      // 失敗メッセージを画面に表示する
      model.addAttribute("msg", msg);

      // 遷移先の新規登録画面で使用するFormを送信
      model.addAttribute("addUserForm", form);
      return ADD_USER_CONFIRM_PAGE;
    }

    //登録処理に成功した場合
    String msg = msgutil.getMessage("data.regist");
    log.info(msg);

    redirectAttributes.addFlashAttribute("status", "add");
    return "redirect:" + SEARCH_FORM_URL;
  }


  /**
   * 新規登録画面遷移処理<br>
   * <pre>
   * 新規登録入力画面へ遷移する。
   * </pre>
   * @param form
   * @param bindingResult
   * @param model
   * @return
   */
  @RequestMapping(value = ADD_USER_URL, method = RequestMethod.POST)
  public String addUser(
      @Validated @ModelAttribute AddUserForm form,
      BindingResult bindingResult,
      Model model){


    // BeanValidationの結果確認
    if (bindingResult.hasErrors()) {

      model.addAttribute("addUserForm", new AddUserForm());
      return ADD_USER_PAGE;
    }

    model.addAttribute("addUserForm", form);

    return ADD_USER_PAGE;
  }
}