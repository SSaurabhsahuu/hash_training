
export class LoggingService {

  logStatusChange(status: string)
  {
    console.log('A server status Changed, new status '+status);
  }
}
