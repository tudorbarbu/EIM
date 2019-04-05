package ro.pub.cs.systems.eim.lab06.clientservercommunication.network;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants;
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Utilities;

public class ClientAsyncTask extends AsyncTask<String, String, Void> {

    private TextView serverMessageTextView;

    public ClientAsyncTask(TextView serverMessageTextView) {
        this.serverMessageTextView = serverMessageTextView;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {

            // TODO exercise 6b
            // - get the connection parameters (serverAddress and serverPort from parameters - on positions 0 and 1)
            // - open a socket to the server
            // - get the BufferedReader in order to read from the socket (use Utilities.getReader())
            // - while the line that has read is not null (EOF was not sent), append the content to serverMessageTextView
            // by publishing the progress - with the publishProgress(...) method - to the UI thread
            // - close the socket to the server
            Socket socket = new Socket(params[0], Integer.parseInt(params[1]));
            BufferedReader br = Utilities.getReader(socket);
            String line = br.readLine();
            Log.d("mytag", line);
            while(line != null) {
                publishProgress(line);
                line = br.readLine();
            }

        } catch (Exception exception) {
            Log.e(Constants.TAG, "An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        // TODO exercise 6b
        // - reset the content of the serverMessageTextView
        serverMessageTextView.setText("");
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        // TODO exercise 6b
        // - append the content to serverMessageTextView
        serverMessageTextView.setText(serverMessageTextView.getText().toString().concat(progress[0]));
    }

    @Override
    protected void onPostExecute(Void result) {}

}
