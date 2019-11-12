package cn.intersteller.darkintersteller.login.view;

public interface ILoginView {
	public void onClearText();
	public void onLoginResult(Boolean result, int returncode, long userid);
	public void onSetProgressBarVisibility(int visibility);
}
