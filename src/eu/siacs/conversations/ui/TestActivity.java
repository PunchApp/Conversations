package eu.siacs.conversations.ui;

import eu.siacs.conversations.R;
import eu.siacs.conversations.entities.Account;
import eu.siacs.conversations.services.XmppConnectionService;
import android.os.Bundle;
import android.widget.Toast;

public class TestActivity extends XmppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_test);


	}

	@Override
	void onBackendConnected() {
		final Account account = new Account("username", "server", "password");

		xmppConnectionService.setOnAccountListChangedListener(new XmppConnectionService.OnAccountUpdate() {

			@Override
			public void onAccountUpdate() {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						if(account.getStatus() == Account.STATUS_ONLINE) {
							Toast.makeText(TestActivity.this, "Account register: Success", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});

		final boolean registerNewAccount = false;

		account.setOption(Account.OPTION_USETLS, true);
		account.setOption(Account.OPTION_USECOMPRESSION, true);
		account.setOption(Account.OPTION_REGISTER, registerNewAccount);
		xmppConnectionService.createAccount(account);
	}
}
