package jp.co.sysystem.springWorkout.web.controller.page;

import static jp.co.sysystem.springWorkout.web.controller.page.LoginController.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sysystem.springWorkout.domain.table.ResultTable;
import jp.co.sysystem.springWorkout.service.UserDeleteService;
import jp.co.sysystem.springWorkout.util.MessageUtil;
import jp.co.sysystem.springWorkout.web.form.LoginForm;
import jp.co.sysystem.springWorkout.web.form.UpdateUserForm;

@Controller
@EnableAutoConfiguration
public class DeleteController {

  @Autowired
  HttpSession session;

  @Autowired
  public MessageUtil msgutil;

  @Autowired
  public UserDeleteService uds;


/// URL定義
  public static final String DELETE_USER_URL = "/deleteUserPage";
  public static final String DELETE_EXE_URL = "/detele_exe";
  public static final String SEARCH_FORM_URL = "/searchPage";

///ページ定義
  public static final String SEARCH_PAGE = "page/search";
  public static final String DELETE_USER_PAGE = "page/delete_user";


  /**
   * 【削除画面のコントローラーに移動予定】
   * 削除ボタンが押された時の処理
   * ResultTableクラスのidフィールドに削除対象のidを保持して削除画面へ遷移
   * @param rt
   * @param model
   * @return
   */
  @RequestMapping(value = DELETE_USER_URL, method = RequestMethod.POST)
  public String deleteUser(
      @ModelAttribute UpdateUserForm form,
      @ModelAttribute ResultTable rt, Model model)
  {

    //セッションにログイン情報が無ければログインページへ遷移
    if (session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //IDからデータを取得する
    String tgtId = rt.getId();
    form = uds.checkDeleteUser(tgtId);

    model.addAttribute("updateUserForm", form);
    return DELETE_USER_PAGE;
  }

  @RequestMapping(value = DELETE_EXE_URL, method = RequestMethod.POST)
  public String delete_exe(
      @ModelAttribute ResultTable rt,UpdateUserForm form,RedirectAttributes redirectAttributes,
      Model model) {

    //セッションにログイン情報が無ければログインページへ遷移
    if (session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    try {

      uds.exeDeleteUser(form.getId());


    }catch(Exception e) {



      String msg = msgutil.getMessage("dbacces.deleteFailed");
      model.addAttribute("msg", msg);
      model.addAttribute("resultTable",rt);
      model.addAttribute("updateUserForm", new UpdateUserForm());
      return DELETE_USER_PAGE;
    }


    //削除実行の後に検索ページへ遷移する
    redirectAttributes.addFlashAttribute("status", "delete");
    return "redirect:" + SEARCH_FORM_URL;

  }


}
