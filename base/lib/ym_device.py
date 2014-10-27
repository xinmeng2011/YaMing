

class ym_device:
    def __inin__(self, rpc):
        self.rpc_connection = rpc
        self.device_serial = None
        self.busy = False
            
    def invoke(self, method, arguments):
        if not self.busy:
            pass
        #raise a exception
        pass
    
    def run_adb_command(self):
        pass
    
    def weakup_whole_app(self, package, clazz):
        pass
        
    def get_serial_no(self):
        return self.device_serial
    
    def is_busy(self):
        return self.busy;
    
    def reserve_device(self):
        self.busy = True
    
    def release_device(self):
        self.busy = False
    
    