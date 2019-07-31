# Implicit Intent & IntentFilters

![](ImplicitIntent_IntentFilter_gif.gif)

## Introduction

The purpose of this app is to show how Implicit intent works and to show how to open a Map & send Text to other applications.

## Implicit Intent

When we want to navigate to another application sending data to it though, and letting it handle it, that's called an implicit intent. Implicit intents don't say exactly which application or which activity they want to navigate to. They simply package up some data and indicate an action they want to take. It's up to other applications to register to handle those actions using something called an intent filter. 


## Navigate To Google Map

Pass some coordinates, a latitude and a longitude. So I've set those up as a simple string example: `public static final String COORDINATES = "51.5298,0.1722";`

In order to pass data, we use a URI, a uniform resource identifier. The uniform resource identifier starts with a prefix. And there are various prefixes for different actions. The prefix for sending data to a mapping application is geo, for geography.

```
public void openMap(View view){
        Uri uri = Uri.parse("geo:"+COORDINATES);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
```

## Send Text To Another Application using Intent Filter

```
public void sendText(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("IMPLICIT_TEXT","Hello, Thank you!");
        intent.setType("text/plain");
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
            Log.i("Main","if method called");
        }
        else{
            Toast.makeText(getApplicationContext(),"No other application to handle this intent",Toast.LENGTH_SHORT).show();
            Log.i("Main","else method called");
        }
    }
```

On onClick `public void sendText(View view){...}` we send text to another application.

Here we are creating a receiving application 
[Send Data To ParentActivity Using RequestCode & Return Result with Up Button in ActionBar.](https://github.com/amansharma-dev/Send_Data_To_ParentActivity_RequestCode_BackButton_ActionBar/ "Send Data To ParentActivity Using RequestCode & Return Result with Up Button in ActionBar.")

In [Send Data To ParentActivity Using RequestCode & Return Result with Up Button in ActionBar.](https://github.com/amansharma-dev/Send_Data_To_ParentActivity_RequestCode_BackButton_ActionBar/ "Send Data To ParentActivity Using RequestCode & Return Result with Up Button in ActionBar.") go to AndroidManifest.xml and add this piece of code to the launcher main activity to handle that intent we have to add intent filter.

```
<intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <data android:mimeType="text/plain"/>
</intent-filter>
```

#### Note : 
We don't have to add the intent-filter to a launcher activity. Any activity of any app, can handle any intent.
In this case we are doing it in a launcher activity.

Now in Main Activity : 

When the activity is launched,we will always get an onResume method call, onResume always happens. So, that's where we will put the code, to handle this intent. Within the onResume method.

```
 @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            String message =  intent.getStringExtra("IMPLICIT_TEXT");
            if (message != null) {
                implicitText.setText(message);
            }
        }
    }
```

That's it.

# Thank You.
